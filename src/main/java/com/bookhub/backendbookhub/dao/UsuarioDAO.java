package com.bookhub.backendbookhub.dao;

import com.bookhub.backendbookhub.Utils.ExceptionUtils;
import com.bookhub.backendbookhub.api.vo.TopicoPutRequestVO;
import com.bookhub.backendbookhub.api.vo.UsuarioPutRequestVO;
import com.bookhub.backendbookhub.entity.TopicoEntity;
import com.bookhub.backendbookhub.entity.UsuarioEntity;
import com.bookhub.backendbookhub.exception.CampoExistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Objects;

import static com.bookhub.backendbookhub.Utils.ExceptionUtils.buscaCampoDuplicado;
import static com.bookhub.backendbookhub.Utils.ExceptionUtils.buscaValorDuplicado;

/**
 * @author Arthur Rio
 * @since 9/16/19
 */
@Repository
@Transactional
public class UsuarioDAO {

 @Autowired
 private EntityManager em;

 public UsuarioEntity find(final Integer id) {
  return em.find(UsuarioEntity.class, id);
 }

 public List findAll() {
  return em.createQuery("from " + UsuarioEntity.class.getSimpleName()).getResultList();
 }

 public UsuarioEntity save(final UsuarioEntity usuarioEntity) throws CampoExistenteException {
  try {
   return em.merge(usuarioEntity);
  } catch (Exception e){

    if(e instanceof SQLIntegrityConstraintViolationException) {
     throw new CampoExistenteException(buscaCampoDuplicado(e.getMessage()), buscaValorDuplicado(e.getMessage()),e);
    }
    throw e;
  }
 }

 public void delete(final Integer id) {
  final UsuarioEntity user = find(id);
  em.remove(user);
 }

 public UsuarioEntity findByLogin(String login){
   return (UsuarioEntity) em.createNativeQuery("select * from usuario where login = :login", UsuarioEntity.class).setParameter("login",login).getSingleResult();
 }

 public UsuarioEntity findByTelefone(String telefone){
   return (UsuarioEntity) em.createNativeQuery("select * from usuario where telefone = :telefone", UsuarioEntity.class).setParameter("telefone",telefone).getSingleResult();
 }

 public UsuarioEntity findByEmail(String email){
  return (UsuarioEntity) em.createNativeQuery("select * from usuario where email = :email", UsuarioEntity.class).setParameter("email",email).getSingleResult();
 }

 public boolean existeUsuario(String telefone, String login, String email){
  try {
   em.createNativeQuery("select * from usuario where telefone = :telefone or login = :login or email = :email", UsuarioEntity.class)
           .setParameter("telefone", telefone)
           .setParameter("login", login)
           .setParameter("email", email)
           .getSingleResult();

    return true;
  } catch (NoResultException e){
    return false;
  }
 }

 public void atualizaUsuairo(UsuarioPutRequestVO usuario){

  UsuarioEntity resultado = em.find(UsuarioEntity.class, usuario.getId());

  if(Objects.nonNull(usuario.getNome())){
   resultado.setNome(usuario.getNome());
  }

  if(Objects.nonNull(usuario.getSobrenome())) {
   resultado.setSobrenome(usuario.getSobrenome());
  }

  if(Objects.nonNull(usuario.getEmail())) {
   resultado.setEmail(usuario.getEmail());
  }

  if(Objects.nonNull(usuario.getDataUltimoAcesso())) {
   resultado.setDataUltimoAcesso(usuario.getDataUltimoAcesso());
  }

  if(Objects.nonNull(usuario.getLogin())) {
   resultado.setLogin(usuario.getLogin());
  }

  if(Objects.nonNull(usuario.getIdAvatar())) {
   resultado.setIdAvatar(usuario.getIdAvatar());
  }


  if(Objects.nonNull(usuario.getSenha())) {
   resultado.setSenha(usuario.getSenha());
  }

  if(Objects.nonNull(usuario.getTelefone())) {
   resultado.setTelefone(usuario.getTelefone());
  }

 }



}
