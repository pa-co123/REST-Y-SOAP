# server.py
"""
Arranca el servidor SOAP con WSGI y expone el WSDL en:
http://localhost:8000/?wsdl
"""
from wsgiref.simple_server import make_server
from soap.server.soap_app import wsgi_app

if __name__ == '__main__':
    port = 8000
    print(f"Starting SOAP server on http://0.0.0.0:{port}/?wsdl")
    server = make_server('0.0.0.0', port, wsgi_app)
    try:
        server.serve_forever()
    except KeyboardInterrupt:
        print("Server stopped by user")
