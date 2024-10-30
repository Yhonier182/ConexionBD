package Modelo;
import java.util.ArrayList;

import Controlador.Coordinador;
import conexion.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogicaCarrito {

        private Coordinador miCoordinador;

        public void setCoordinador(Coordinador miCoordinador) {
            this.miCoordinador = miCoordinador;
        }

    public ArrayList<String> listar(String documento) {
        ArrayList<String> lista = new ArrayList<>();
        String consulta = "SELECT P.nombreProducto AS nombre, P.precio FROM producto P JOIN usuario_tiene_producto UtP ON UtP.idProducto = P.idProducto WHERE UtP.documento = ?";

        try (Connection connection = Conexion.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(consulta)) {

            statement.setString(1, documento);
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    lista.add(result.getString("nombre") + " " + result.getInt("precio") + "$");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar productos en el carrito: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        return lista;
    }

    public boolean comprar(String idProducto, String documento) {
        boolean resultado = false;
        String consulta = "INSERT INTO usuario_tiene_producto (idProducto, documento) VALUES (?, ?)";

        try (Connection connection = Conexion.getInstance().getConnection();
             PreparedStatement preStatement = connection.prepareStatement(consulta)) {

            preStatement.setString(1, idProducto);
            preStatement.setString(2, documento);
            preStatement.execute();
            resultado = true;

        } catch (SQLException e) {
            System.out.println("No se pudo registrar la compra: " + e.getMessage());
            e.printStackTrace();
        }
        return resultado;
    }
}