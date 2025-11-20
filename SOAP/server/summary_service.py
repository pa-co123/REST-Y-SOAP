# soap/server/summary_service.py
from spyne import ServiceBase, rpc, Integer, Date, Boolean
from soap.client.summary_client import fetch_daily_summary
from config.database import execute
from datetime import datetime

class SummaryService(ServiceBase):
    @rpc(Integer, Date, Integer, _returns=Boolean)
    def FetchAndStoreDailySummary(ctx, id_usuario, fecha_dia, id_dispositivo):
        """
        Llama al cliente Zeep (fetch_daily_summary) para obtener datos del resumen diario
        y los inserta en la tabla ResumenActividadDiaria.
        Retorna True si OK.
        """
        # Llamada al cliente (aquí usamos el cliente local que devuelve ejemplo)
        data = fetch_daily_summary(id_usuario, fecha_dia.isoformat())
        if not data:
            return False

        query = """
            INSERT INTO resumen_actividad_diaria
            (id_usuario, id_dispositivo, fecha_dia, pasos, distancia_km, calorias_quemadas, minutos_activo, minutos_sedentario)
            VALUES (%s, %s, %s, %s, %s, %s, %s, %s);
        """
        params = (
            id_usuario,
            id_dispositivo,
            fecha_dia,
            data['pasos'],
            data['distancia_km'],
            data['calorias_quemadas'],
            data['minutos_activo'],
            data['minutos_sedentario'],
        )
        execute(query, params)
        return True
