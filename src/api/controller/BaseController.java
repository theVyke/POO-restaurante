package api.controller;

import api.model.BaseEntity;

import java.util.List;

public abstract class BaseController<T extends BaseEntity> {

    /**
     * Método para salvar uma entidade.
     *
     * @param entity Entidade a ser salva.
     * @return Entidade salva.
     */
    public abstract T save(T entity);

    /**
     * Método para buscar uma entidade pelo ID.
     *
     * @param id ID da entidade a ser buscada.
     * @return Entidade encontrada.
     */
    public abstract T findById(int id);

    /**
     * Método para buscar todas as entidades.
     *
     * @return Lista de entidades encontradas.
     */
    public abstract List<T> findAll();

    /**
     * Método para deletar uma entidade pelo ID.
     *
     * @param id ID da entidade a ser deletada.
     */
    public abstract void delete(int id);
}
