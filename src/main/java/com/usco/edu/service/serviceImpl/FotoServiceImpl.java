package com.usco.edu.service.serviceImpl;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IFotoDao;
import com.usco.edu.dto.Foto;
import com.usco.edu.dto.RespuestaVerFoto;
import com.usco.edu.feing.EnviarFotoClient;
import com.usco.edu.service.IFotoService;

@Service
public class FotoServiceImpl implements IFotoService {

    @Autowired
    private EnviarFotoClient enviarFotoClient;

    @Autowired
    private IFotoDao fotoCarnetDao;

    // MÉTODO PARA VALIDAR SI UN NOMBRE DE ARCHIVO ES VÁLIDO
    public boolean isValido(String nombre) {
        String expresion = "^([[a-zA-Z][0-9]_]{2,150})$";
        try {
            Pattern p = Pattern.compile(expresion);
            Matcher matcher = p.matcher(nombre);
            return matcher.matches();
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    // MÉTODO PARA OBTENER UNA FOTO Y CONVERTIRLA EN UN FLUJO DE ENTRADA
    @Override
    public ByteArrayInputStream mirarFoto(String perCodigo, HttpServletResponse response) {

        byte[] array = { 1, 2, 3, 4 };

        // OBTENER UNA CLAVE/TOKEN PARA LA FOTO
        String Key = fotoCarnetDao.obtenerTokenFoto(perCodigo);

        RespuestaVerFoto respuesta = new RespuestaVerFoto();

        try {
            // OBTENER LA FOTO DEL SISTEMA EXTERNO
            respuesta = enviarFotoClient.mostrarFoto(perCodigo, Key);

            byte[] fotoBytes = Base64.getDecoder().decode(respuesta.getBase64().split(",")[1]);
            return new ByteArrayInputStream(fotoBytes);

        } catch (Exception e) {
            System.out.println(e);
        }

        return new ByteArrayInputStream(array);
    }

    // MÉTODO PARA OBTENER UNA FOTO ANTIGUA Y DEVOLVERLA COMO UN OBJETO FOTOANTIGUA
    @Override
    public Foto mirarFotoAntigua(String perCodigo) {
        String aux = "";
        String Key = fotoCarnetDao.obtenerTokenFoto(perCodigo);
        aux = enviarFotoClient.mostrarFotoAntigua(perCodigo, Key).toString();
        Foto foto = new Foto();
        foto.setUrl(aux);
        return foto;
    }
}
