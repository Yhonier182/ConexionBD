    package Dao;

    import Modelo.Productos;
    import conexion.Conexion;

    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.List;
    import javax.swing.JTable;
    import javax.swing.table.DefaultTableModel;

    public class ProductoDao {

        // Método para registrar un producto
        public String registrarProducto(Productos producto) {
            String resultado = "error"; // Definir valor por defecto
            String consulta = "INSERT INTO producto (nombre, precio) VALUES (?, ?)";

            // Usar try-with-resources para manejar la conexión, PreparedStatement y ResultSet
            try (Connection connection = new Conexion().getConnection();
                 PreparedStatement preStatement = connection.prepareStatement(consulta)) {

                preStatement.setString(1, producto.getNombre());
                preStatement.setDouble(2, producto.getPrecio());
                preStatement.execute();

                resultado = "ok";

            } catch (SQLException e) {
                System.out.println("No se pudo registrar el producto: " + e.getMessage());
                e.printStackTrace();
            }

            return resultado;
        }

        // Método para consultar un producto por nombre
        public Productos consultarProducto(String nombre) {
            Productos producto = null;
            String consulta = "SELECT * FROM producto WHERE nombre = ?";

            try (Connection connection = new Conexion().getConnection();
                 PreparedStatement statement = connection.prepareStatement(consulta)) {

                statement.setString(1, nombre);

                try (ResultSet result = statement.executeQuery()) {
                    if (result.next()) {
                        producto = new Productos();
                        producto.setId(result.getInt("id"));
                        producto.setNombre(result.getString("nombre"));
                        producto.setPrecio(result.getDouble("precio"));
                    }
                }

            } catch (SQLException e) {
                System.out.println("Error en la consulta del producto: " + e.getMessage());
            }

            return producto;
        }




        public List<Productos> obtenerProductos() {
            List<Productos> listaProductos = new ArrayList<>();
            String sql = "SELECT * FROM producto";

            try (Connection connection = new Conexion().getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Productos producto = new Productos();
                    producto.setId(resultSet.getInt("id"));
                    producto.setNombre(resultSet.getString("nombre"));
                    producto.setPrecio(resultSet.getDouble("precio"));
                    listaProductos.add(producto);
                }

                // Agregar depuración para asegurarse de que los productos están siendo recuperados
                System.out.println("Productos recuperados:");
                for (Productos p : listaProductos) {
                    System.out.println(p.getNombre() + " - " + p.getPrecio());
                }

            } catch (SQLException e) {
                System.out.println("Error al obtener productos: " + e.getMessage());
            }

            return listaProductos;
        }





        // Método para actualizar un producto
        public String actualizarProducto(Productos producto) {
            String resultado = "error";  // Valor por defecto

            String consulta = "UPDATE producto SET nombre = ?, precio = ? WHERE id = ?";
            try (Connection connection = new Conexion().getConnection();
                 PreparedStatement preStatement = connection.prepareStatement(consulta)) {

                preStatement.setString(1, producto.getNombre());
                preStatement.setDouble(2, producto.getPrecio());
                preStatement.setInt(3, producto.getId()); // Usamos el ID para identificar el producto

                preStatement.executeUpdate();
                resultado = "ok";

            } catch (SQLException e) {
                System.out.println(e);
            }

            return resultado;
        }

        // Método para eliminar un producto
        public String eliminarProducto(int id) {
            String resp = "error";  // Valor por defecto

            String sentencia = "DELETE FROM producto WHERE id = ?";
            try (Connection connection = new Conexion().getConnection();
                 PreparedStatement statement = connection.prepareStatement(sentencia)) {

                statement.setInt(1, id);
                statement.executeUpdate();
                resp = "ok";

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            return resp;
        }
    }
