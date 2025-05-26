package api.repository.impl;

import java.util.List;

import api.model.Prato;
import api.repository.Repository;

public class PratoRepository implements Repository<Prato> {

    private List<Prato> pratos;

    public PratoRepository(List<Prato> pratos) {
        this.pratos = pratos;
    }

    @Override
    public Prato save(Prato entity) {
        // Verifica se o prato já existe na lista
        for (Prato prato : pratos) {
            if (prato.getId() == entity.getId()) {
                // Se já existe, atualiza o prato
                prato.setNome(entity.getNome());
                prato.setPreco(entity.getPreco());
                return prato;
            }
        }
        // Se não existe, adiciona o novo prato à lista
        pratos.add(entity);
        return entity;

    }

    @Override
    public Prato findById(int id) {

        // Percorre a lista de pratos e retorna o prato com o ID correspondente
        for (Prato prato : pratos) {
            if (prato.getId() == id) {
                return prato;
            }
        }
        // Se não encontrar, retorna null
        return null;
    }

    @Override
    public List<Prato> findAll() {
        return pratos;
    }

    @Override
    public void delete(int id) {

        // Percorre a lista de pratos e remove o prato com o ID correspondente
        for (int i = 0; i < pratos.size(); i++) {
            if (pratos.get(i).getId() == id) {
                pratos.remove(i);
                break;
            }
        }
    }
    
}
