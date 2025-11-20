# soap/server/heart_rate_service.py
from spyne import ServiceBase, rpc, Integer, DateTime, Boolean
from datetime import datetime
from config.database import execute

class HeartRateService(ServiceBase):
    @rpc(Integer, Integer, Integer, DateTime, Integer, _returns=Boolean)
    def RegisterHeartRate(ctx, id_usuario, id_dispositivo, bpm, fecha_registro, calidad_medicion):
        """
        Registra una frecuencia cardiaca. Retorna True si OK.
        fecha_registro puede pasarse desde el cliente o NULL (en este caso el servicio espera DateTime).
        """
        # Si fecha_registro es None o no válida, MySQL TIMESTAMP con NOW() se puede usar. Aquí asumimos que viene.
        query = """
            INSERT INTO registro_frecuencia_cardiaca (id_usuario, id_dispositivo, bpm, fecha_registro, calidad_medicion)
            VALUES (%s, %s, %s, %s, %s)
        """
        params = (id_usuario, id_dispositivo, bpm, fecha_registro, calidad_medicion)
        execute(query, params)
        return True
