# config/database.py
import pymysql
from pymysql.cursors import DictCursor
from contextlib import contextmanager
import config.db_config as db_config

@contextmanager
def get_connection():
    conn = pymysql.connect(
        host=db_config.DB_HOST,
        port=db_config.DB_PORT,
        user=db_config.DB_USER,
        password=db_config.DB_PASSWORD,
        db=db_config.DB_NAME,
        cursorclass=DictCursor,
        autocommit=False,
        charset='utf8mb4'
    )
    try:
        yield conn
    finally:
        try:
            conn.close()
        except Exception:
            pass

def fetchone(query, params=None):
    with get_connection() as conn:
        with conn.cursor() as cur:
            cur.execute(query, params or ())
            return cur.fetchone()

def fetchall(query, params=None):
    with get_connection() as conn:
        with conn.cursor() as cur:
            cur.execute(query, params or ())
            return cur.fetchall()

def execute(query, params=None):
    """
    Ejecuta INSERT/UPDATE/DELETE. Retorna cursor.lastrowid (si aplica).
    """
    with get_connection() as conn:
        with conn.cursor() as cur:
            cur.execute(query, params or ())
            lastrowid = cur.lastrowid
        conn.commit()
    return lastrowid
