package modelo

import java.sql.Connection
import java.sql.DriverManager

class Conexion {

    fun cadenaConexion(): Connection? {

        try {
            val url = "jdbc:oracle:thin:@192.168.1.18:1521:xe"
            val usuario = "KATHERINE_GUILLEN"
            val contrasena = "220615"

            val connetion  = DriverManager.getConnection(url,usuario,contrasena)
            return  connetion
        } catch (e: Exception){
            println("Error: $e")
            return null
        }
    }
}