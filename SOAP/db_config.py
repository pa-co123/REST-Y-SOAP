# config/db_config.py
DB_HOST = "127.0.0.1"
DB_PORT = 3306
DB_USER = "root"
DB_PASSWORD = "!Embaroaxtellx377"
DB_NAME = "iot_soap_db"
import os
from dotenv import load_dotenv

load_dotenv()  # Carga variables desde un archivo .env

DB_HOST = os.getenv("DB_HOST", DB_HOST)
DB_PORT = int(os.getenv("DB_PORT", DB_PORT))
DB_USER = os.getenv("DB_USER", DB_USER)
DB_PASSWORD = os.getenv("DB_PASSWORD", DB_PASSWORD)
DB_NAME = os.getenv("DB_NAME", DB_NAME)