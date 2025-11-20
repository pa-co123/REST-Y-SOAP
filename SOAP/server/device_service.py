# soap/server/device_service.py
from spyne import ServiceBase, rpc, Integer, Unicode, Boolean, DateTime, ComplexModel, Array
from spyne.model.primitive import UnsignedInteger
from datetime import datetime
from config.database import fetchone, fetchall, execute

class Device(ComplexModel):
    id_dispositivo = Integer
    id_usuario = Integer
    tipo_dispositivo = Unicode
    modelo = Unicode
    numero_serie = Unicode
    fecha_registro = DateTime
    activo = Boolean

class DeviceService(ServiceBase):
    @rpc(Integer, Unicode, Unicode, Unicode, _returns=Integer)
    def CreateDevice(ctx, id_usuario, tipo_dispositivo, modelo, numero_serie):
        """
        Inserta un dispositivo. Retorna id_dispositivo (nuevo).
        fecha_registro se llena con NOW() en MySQL.
        """
        query = """
            INSERT INTO dispositivos_iot (id_usuario, tipo_dispositivo, modelo, numero_serie, fecha_registro, activo)
            VALUES (%s, %s, %s, %s, NOW(), 1)
        """
        params = (id_usuario, tipo_dispositivo, modelo, numero_serie)
        new_id = execute(query, params)
        return new_id or 0

    @rpc(Integer, _returns=Device)
    def GetDevice(ctx, id_dispositivo):
        query = "SELECT id_dispositivo, id_usuario, tipo_dispositivo, modelo, numero_serie, fecha_registro, activo FROM dispositivos_iot WHERE id_dispositivo = %s"
        row = fetchone(query, (id_dispositivo,))
        if not row:
            return None
        # Spyne espera tipos correctos; fecha_registro debe ser datetime
        return Device(
            id_dispositivo=row['id_dispositivo'],
            id_usuario=row['id_usuario'],
            tipo_dispositivo=row['tipo_dispositivo'],
            modelo=row['modelo'],
            numero_serie=row['numero_serie'],
            fecha_registro=row['fecha_registro'],
            activo=bool(row['activo'])
        )

    @rpc(Integer, Integer, Unicode, Unicode, Unicode, Boolean, _returns=Boolean)
    def UpdateDevice(ctx, id_dispositivo, id_usuario, tipo_dispositivo, modelo, numero_serie, activo):
        query = """
            UPDATE dispositivos_iot
            SET tipo_dispositivo=%s, modelo=%s, numero_serie=%s, activo=%s, id_usuario=%s
            WHERE id_dispositivo=%s
        """
        params = (tipo_dispositivo, modelo, numero_serie, activo, id_usuario, id_dispositivo)
        execute(query, params)
        return True

    @rpc(Integer, _returns=Boolean)
    def DeleteDevice(ctx, id_dispositivo):
        query = "DELETE FROM dispositivos WHERE id_dispositivo = %s"
        query = "DELETE FROM dispositivos_iot WHERE id_dispositivo = %s"
        execute(query, (id_dispositivo,))
        return True

    @rpc(_returns=Array(Device))
    def ListDevices(ctx):
        query = "SELECT id_dispositivo, id_usuario, tipo_dispositivo, modelo, numero_serie, fecha_registro, activo FROM dispositivos"
        query = "SELECT id_dispositivo, id_usuario, tipo_dispositivo, modelo, numero_serie, fecha_registro, activo FROM dispositivos_iot"
        rows = fetchall(query)
        result = []
        for row in rows:
            result.append(Device(
                id_dispositivo=row['id_dispositivo'],
                id_usuario=row.get('id_usuario'),
                tipo_dispositivo=row.get('tipo_dispositivo'),
                modelo=row.get('modelo'),
                numero_serie=row.get('numero_serie'),
                fecha_registro=row.get('fecha_registro'),
                activo=bool(row.get('activo'))
            ))
        return result
