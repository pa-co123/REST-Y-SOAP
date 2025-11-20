# soap/client/summary_client.py
import os
from pathlib import Path
from zeep import Client, Transport
from zeep.exceptions import Fault
from zeep.cache import InMemoryCache
import random

# Construye la ruta correcta al archivo WSDL
# Usamos pathlib para construir una URL de archivo compatible con todos los SO
WSDL_PATH = Path(__file__).parent / 'wsdl' / 'wsdl.xml'
WSDL_URL = WSDL_PATH.as_uri()

# El cliente Zeep para interactuar con el servicio SOAP (en este caso, el falso local)
# Usamos un caché en memoria para no tener que parsear el WSDL en cada llamada
transport = Transport(cache=InMemoryCache())
client = Client(wsdl=WSDL_URL, transport=transport)

def fetch_daily_summary(user_id, date_str):
    """
    Llama a la operación GetDailySummary del servicio SOAP falso.
    En un caso real, aquí se haría la llamada a un servicio externo.
    Para este ejemplo, generamos datos aleatorios para simular la respuesta.
    """
    try:
        # En una implementación real, llamarías al servicio:
        # response = client.service.GetDailySummary(userId=user_id, date=date_str)
        # return response

        # --- Inicio de la simulación ---
        # Como no tenemos un servidor corriendo para el WSDL falso,
        # simulamos la respuesta con datos aleatorios.
        print(f"Simulando obtención de datos para el usuario {user_id} en la fecha {date_str}")
        simulated_data = {
            'pasos': random.randint(3000, 15000),
            'distancia_km': round(random.uniform(2.0, 10.0), 2),
            'calorias_quemadas': round(random.uniform(150.0, 600.0), 2),
            'minutos_activo': random.randint(30, 120),
            'minutos_sedentario': random.randint(400, 800)
        }
        return simulated_data
        # --- Fin de la simulación ---

    except Fault as f:
        print(f"Error en la llamada SOAP: {f.message}")
        return None
    except Exception as e:
        print(f"Ocurrió un error inesperado al llamar al servicio: {e}")
        return None
