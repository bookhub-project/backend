package com.bookhub.backendbookhub.service;

import com.bookhub.backendbookhub.api.vo.UsuarioPostRequestVO;
import com.bookhub.backendbookhub.api.vo.UsuarioPostResponseVO;
import com.bookhub.backendbookhub.dao.UsuarioDAO;
import com.bookhub.backendbookhub.entity.UsuarioEntity;
import com.bookhub.backendbookhub.exception.CampoExistenteException;
import com.bookhub.backendbookhub.exception.UsuarioExistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;


    public UsuarioPostResponseVO save(UsuarioPostRequestVO usuarioPostRequestVO) throws CampoExistenteException, UsuarioExistenteException {


        if( usuarioDAO.existeUsuario(usuarioPostRequestVO.getTelefone(),
                usuarioPostRequestVO.getLogin(),usuarioPostRequestVO.getEmail())) {
            throw new UsuarioExistenteException(usuarioPostRequestVO.getTelefone(),usuarioPostRequestVO.getLogin());
        }

        UsuarioEntity usuario = usuarioDAO.save(usuarioPostRequestVO.toUsuarioEntity());

        return UsuarioPostResponseVO.builder()
                .email(usuario.getEmail())
                .id(usuario.getId())
                .idAvatar(usuario.getIdAvatar())
                .nome(usuario.getNome())
                .telefone(usuario.getTelefone())
                .login(usuario.getLogin())
                .build();

    }

    private void verificaUsuarioCadastrado(UsuarioPostRequestVO request){

    }


    public List findAll(){
        return usuarioDAO.findAll();
    }

    public void delete(final Integer id) {
        usuarioDAO.delete(id);
    }

    public UsuarioEntity find(final Integer id){
        return usuarioDAO.find(id);
    }


}
