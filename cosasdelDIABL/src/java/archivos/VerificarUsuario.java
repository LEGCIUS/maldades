package archivos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VerificarUsuario {
    private static final Logger logger = Logger.getLogger(VerificarUsuario.class.getName());
    String Maldades = "123456789ABCDEFGHIJKMNÑOPQRSTUVWXYZ";
    String rutaArchivo = "C:\\Users\\usuario\\OneDrive\\Documentos\\NetBeansProjects\\cosasdelDIABL\\Usuarios.txt";

    public String verificarUsuario(String email, String contraseña) {
        String[] datosCodificados = codificar(Maldades, email, contraseña);
        String emailEncriptado = datosCodificados[0];
        String contraseñaEncriptada = datosCodificados[1];

        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] datosArchivo = line.split(",");
                if (datosArchivo.length >= 3) {
                    String emailArchivo = datosArchivo[1];
                    String contraseñaArchivo = datosArchivo[2];
                    if (emailArchivo.equals(emailEncriptado) && contraseñaArchivo.equals(contraseñaEncriptada)) {
                        return descodificar(Maldades, datosArchivo[0]); // Retornar el nombre de usuario descodificado
                    }
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error al leer el archivo", e);
        }
        return null;
    }

    public static String[] codificar(String Maldades, String... datos) {
        String[] codificados = new String[datos.length];
        for (int j = 0; j < datos.length; j++) {
            StringBuilder textoCodificado = new StringBuilder();
            String dato = datos[j].toUpperCase();
            for (int i = 0; i < dato.length(); i++) {
                char caracter = dato.charAt(i);
                int pos = Maldades.indexOf(caracter);
                if (pos == -1) {
                    textoCodificado.append(caracter);
                } else {
                    textoCodificado.append(Maldades.charAt((pos + 5) % Maldades.length()));
                }
            }
            codificados[j] = textoCodificado.toString();
        }
        return codificados;
    }

    public static String descodificar(String Maldades, String datoCodificado) {
        StringBuilder textoDescodificado = new StringBuilder();
        for (int i = 0; i < datoCodificado.length(); i++) {
            char caracter = datoCodificado.charAt(i);
            int pos = Maldades.indexOf(caracter);
            if (pos == -1) {
                textoDescodificado.append(caracter);
            } else {
                textoDescodificado.append(Maldades.charAt((pos - 5 + Maldades.length()) % Maldades.length()));
            }
        }
        return textoDescodificado.toString();
    }
}
