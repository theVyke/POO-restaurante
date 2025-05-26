package api.service;

import java.util.List;
import java.util.NoSuchElementException;

import api.model.Prato;
import api.repository.Repository;

public class PratoService extends BaseService<Prato>{
    
    //Instanciação de um repositório do tipo prato
    private Repository<Prato> repository;

    //Construtor do repositorio
    public PratoService(Repository<Prato> repository) {
        this.repository = repository;
    }

    @Override
    public Prato save(Prato entity) {
        //Condição: Nome do prato não pode ser nulo
        if (entity.getNome() == "" || entity.getNome() == null) {
            throw new IllegalArgumentException("Nome do prato não pode ser vazio.");
        }
        //Condição: Preço do prato tem que ser maior que zero
        if (entity.getPreco() <= 0.0) {
            throw new IllegalArgumentException("Preço do prato deve ser maior do que zero.");
        }
        //Se falso, chama o repositorio e salva o prato
        return repository.save(entity);
    }

    public Prato update(int id, String novoNome, String novaDescricao, double novoPreco) {
        Prato prato = findById(id);
        prato.setNome(novoNome);
        prato.setDescricao(novaDescricao);
        prato.setPreco(novoPreco);
        return save(prato); // Reaproveita o método save para atualizar
    }

    @Override
    public Prato findById(int id) {

        //Variavel auxiliar para armazenar a consulta
        Prato pratoConsultado = repository.findById(id);
        //Condição: Retorna exceção se não existir prato com o ID consultado
        if (pratoConsultado == null) {
            throw new NoSuchElementException("Prato com o ID " + id + " não foi encontrado.");
        }

        return pratoConsultado;
    }

    @Override
    public List<Prato> findAll() {
        List<Prato> pratosArmazenados = repository.findAll();
        
        if (pratosArmazenados.isEmpty()) {
            throw new NoSuchElementException("Não há pratos cadastrados.");
        }
        
        return pratosArmazenados;
    }


    @Override
    public void delete(int id) {
        Prato entity;

        entity = repository.findById(id);
        if (entity == null) {
            throw new NoSuchElementException("Prato com o ID " + id + " não foi encontrado.");
       }
        repository.delete(id);
    } 
    


}
