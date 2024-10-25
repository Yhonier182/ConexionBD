    package Dao;

    import Controlador.Coordinador;
    import Modelo.ProductoVo;

    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.ArrayList;

    public class ProductoDao {

        private Coordinador miCoordinador;

        // Método para asignar el coordinador
        public void setCoordinador(Coordinador miCoordinador) {
            this.miCoordinador = miCoordinador;
        }

        // Método para registrar un producto
        public boolean registrarProducto(ProductoVo miProducto) {
            boolean resultado = false;
            Connection connection = null;
            PreparedStatement preStatement = null;

            String consulta = "INSERT INTO producto (idProducto, nombre, precio, cantidad) VALUES (?, ?, ?, ?)";

            try {
                connection = miCoordinador.getConexion().getConnection();
                preStatement = connection.prepareStatement(consulta);
                preStatement.setString(1, miProducto.getIdProducto());
                preStatement.setString(2, miProducto.getNombre());
                preStatement.setInt(3, miProducto.getPrecio());
                preStatement.setInt(4, miProducto.getCantidad());
                preStatement.execute();
                resultado = true;

            } catch (SQLException e) {
                System.out.println("Error al registrar producto: " + e.getMessage());
                e.printStackTrace();
            } finally {
                if (preStatement != null) {
                    try {
                        preStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            return resultado;
        }

        // Método para listar todos los productos
        public ArrayList<ProductoVo> listarProductos() {
            ArrayList<ProductoVo> listaProductos = new ArrayList<>();
            String consulta = "SELECT * FROM producto";
            try (Connection connection = miCoordinador.getConexion().getConnection();
                 PreparedStatement statement = connection.prepareStatement(consulta);
                 ResultSet result = statement.executeQuery()) {

                while (result.next()) {
                    ProductoVo producto = new ProductoVo();
                    producto.setIdProducto(result.getString("idProducto"));
                    producto.setNombre(result.getString("nombre"));
                    producto.setPrecio(result.getInt("precio"));
                    producto.setCantidad(result.getInt("cantidad"));
                    listaProductos.add(producto);
                }

            } catch (SQLException e) {
                System.out.println("Error al listar productos: " + e.getMessage());
            }
            return listaProductos;
        }

        // Método para consultar un producto por nombre
        public ProductoVo consultarProductoPorNombre(String nombre) {
            ProductoVo producto = null;
            String consulta = "SELECT * FROM producto WHERE nombre = ?";
            try (Connection connection = miCoordinador.getConexion().getConnection();
                 PreparedStatement statement = connection.prepareStatement(consulta)) {

                statement.setString(1, nombre);
                try (ResultSet result = statement.executeQuery()) {
                    if (result.next()) {
                        producto = new ProductoVo();
                        producto.setIdProducto(result.getString("idProducto"));
                        producto.setNombre(result.getString("nombre"));
                        producto.setPrecio(result.getInt("precio"));
                        producto.setCantidad(result.getInt("cantidad"));
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error al consultar producto por nombre: " + e.getMessage());
            }
            return producto;
        }

        // Método para consultar un producto por ID
        public ProductoVo consultarProducto(String idProducto) {
            ProductoVo producto = null;
            String consulta = "SELECT * FROM producto WHERE idProducto = ?";
            try (Connection connection = miCoordinador.getConexion().getConnection();
                 PreparedStatement statement = connection.prepareStatement(consulta)) {

                statement.setString(1, idProducto);
                try (ResultSet result = statement.executeQuery()) {
                    if (result.next()) {
                        producto = new ProductoVo();
                        producto.setIdProducto(result.getString("idProducto"));
                        producto.setNombre(result.getString("nombre"));
                        producto.setCantidad(result.getInt("cantidad"));
                        producto.setPrecio(result.getInt("precio"));
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error al consultar producto por ID: " + e.getMessage());
            }
            return producto;
        }

        // Método para actualizar un producto
        public boolean actualizarProducto(ProductoVo producto) {
            boolean resultado = false;
            String consulta = "UPDATE producto SET nombre = ?, cantidad = ?, precio = ? WHERE idProducto = ?";
            try (Connection connection = miCoordinador.getConexion().getConnection();
                 PreparedStatement preStatement = connection.prepareStatement(consulta)) {

                preStatement.setString(1, producto.getNombre());
                preStatement.setInt(2, producto.getCantidad());
                preStatement.setInt(3, producto.getPrecio());
                preStatement.setString(4, producto.getIdProducto());
                preStatement.executeUpdate();
                resultado = true;

            } catch (SQLException e) {
                System.out.println("Error al actualizar producto: " + e.getMessage());
            }
            return resultado;
        }

        // Método para eliminar un producto
        public boolean eliminarProducto(String idProducto) {
            boolean resultado = false;
            String consulta1 = "DELETE FROM producto WHERE idProducto = ?";
            String consulta2 = "DELETE FROM usuario_tiene_producto WHERE idProducto = ?";
            try (Connection connection = miCoordinador.getConexion().getConnection();
                 PreparedStatement preStatement1 = connection.prepareStatement(consulta1);
                 PreparedStatement preStatement2 = connection.prepareStatement(consulta2)) {

                preStatement1.setString(1, idProducto);
                preStatement1.executeUpdate();

                preStatement2.setString(1, idProducto);
                preStatement2.executeUpdate();

                resultado = true;

            } catch (SQLException e) {
                System.out.println("Error al eliminar producto: " + e.getMessage());
            }
            return resultado;
        }
    }
