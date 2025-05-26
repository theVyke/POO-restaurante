package api.controller;

import java.util.List;
import java.util.NoSuchElementException;

import api.model.Prato;
import api.service.PratoService;

public class PratoController extends BaseController<Prato>{

    private PratoService pratoService;

    public PratoController(PratoService pratoService) {
        this.pratoService = pratoService;
    }

    @Override
    public Prato save(Prato entity) {
        
        return pratoService.save(entity);
    }

    public Prato update(int id, String novoNome, String novaDescricao, double novoPreco) {
        return pratoService.update(id, novoNome, novaDescricao, novoPreco);
    }

    @Override
    public Prato findById(int id) {
        return pratoService.findById(id);
    }

    @Override
    public List<Prato> findAll() {
        //Trata a exceção e retorna uma lista vazia (Controller não deve imprimir mensagens, isso cabe ao Menu)
        try {
            return pratoService.findAll();
        } catch (NoSuchElementException e) {
            return List.of();
        }

    }

    @Override
    public void delete(int id) {
        pratoService.delete(id);
    }

    

    
}
