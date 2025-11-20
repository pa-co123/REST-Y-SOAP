# soap/server/soap_app.py
from spyne import Application
from spyne.protocol.soap import Soap11
from spyne.server.wsgi import WsgiApplication

from soap.server.device_service import DeviceService
from soap.server.heart_rate_service import HeartRateService
from soap.server.summary_service import SummaryService

# Namespace para el servicio (ajústalo si quieres)
tns = "http://example.com/iot/soap"

# Cada servicio puede ser registrado dentro de la misma Application
application = Application(
    [DeviceService, HeartRateService, SummaryService],
    tns=tns,
    in_protocol=Soap11(validator='lxml'),
    out_protocol=Soap11()
)

wsgi_app = WsgiApplication(application)
