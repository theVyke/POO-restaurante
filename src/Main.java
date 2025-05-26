import java.util.ArrayList;
import java.util.List;

import api.controller.ItemPedidoController;
import api.controller.PedidoController;
import api.controller.PratoController;
import api.model.Prato;
import api.repository.PedidoRepository;
import api.repository.impl.ItemPedidoRepository;
import api.repository.impl.PedidoRepositoryList;
import api.repository.impl.PedidoRepositoryMap;
import api.repository.impl.PratoRepository;
import api.service.ItemPedidoService;
import api.service.PedidoService;
import api.service.PratoService;
import app.ui.CLI;
import app.ui.InputReader;
import app.ui.menu.ItemPedidoMenu;
import app.ui.menu.PratoMenu;

public class Main {
    public static void main(String[] args) {

        
        List<Prato> pratosTeste = new ArrayList<>();

        Prato prato1 = new Prato("Pizza", "Pizza de Calamussa", 35.0);
        Prato prato2 = new Prato("Hamburguer", "Hamburguer Artesanal Fit", 24.90);
        Prato prato3 = new Prato("Sorvete", "Sorvete de Creme", 5.0);
        Prato prato4 = new Prato("Lasanha", "Lasanha à Bolonhesa", 45.0);
        Prato prato5 = new Prato("Risoto", "Risoto de Camarão", 49.5);
        Prato prato6 = new Prato("Tacos", "Tacos Mexicanos Apimentados", 32.0);
        Prato prato7 = new Prato("Frango Assado", "Frango Assado com Batatas", 40.0);
        Prato prato8 = new Prato("Feijoada", "Feijoada Completa", 55.0);
        Prato prato9 = new Prato("Panqueca", "Panqueca de Carne com Molho", 31.0);
        Prato prato10 = new Prato("Bife Acebolado", "Bife ao Molho de Cebolas", 42.0);
        Prato prato11 = new Prato("Carpaccio", "Carpaccio de Filé Mignon", 47.0);
        Prato prato12 = new Prato("Espaguete", "Espaguete ao Molho Alfredo", 27.0);
        Prato prato13 = new Prato("Churrasco", "Picanha Grelhada com Acompanhamentos", 75.0);
        Prato prato14 = new Prato("Salada Caesar", "Salada Caesar com Frango", 25.0);
        Prato prato15 = new Prato("Sorvete", "Sorvete Artesanal de Chocolate", 20.0);

        pratosTeste.add(prato1);
        pratosTeste.add(prato2);
        pratosTeste.add(prato3);
        pratosTeste.add(prato4);
        pratosTeste.add(prato5);
        pratosTeste.add(prato6);
        pratosTeste.add(prato7);
        pratosTeste.add(prato8);
        pratosTeste.add(prato9);
        pratosTeste.add(prato10);
        pratosTeste.add(prato11);
        pratosTeste.add(prato12);
        pratosTeste.add(prato13);
        pratosTeste.add(prato14);
        pratosTeste.add(prato15);

        // Backend entity
        PratoRepository pratoRepository = new PratoRepository(pratosTeste);
        PratoService pratoService = new PratoService(pratoRepository);
        PratoController pratoController = new PratoController(pratoService);

        boolean usarMap = true; // Decide qual implementação de repositório usar

        PedidoRepository pedidoRepository;
        if (usarMap) {
            pedidoRepository = new PedidoRepositoryMap();
        } else {
            pedidoRepository = new PedidoRepositoryList(new ArrayList<>());
        }
        PedidoService pedidoService = new PedidoService(pedidoRepository, pratoRepository);
        PedidoController pedidoController = new PedidoController(pedidoService);

        ItemPedidoRepository itemPedidoRepository = new ItemPedidoRepository(new ArrayList<>());
        ItemPedidoService itemPedidoService = new ItemPedidoService(
            itemPedidoRepository,
            pedidoRepository,
            pratoRepository
            );
        ItemPedidoController itemPedidoController = new ItemPedidoController(itemPedidoService);

        // Frontend entity
        PratoMenu pratoMenu = new PratoMenu(pratoController);
        ItemPedidoMenu itemPedidoMenu = new ItemPedidoMenu(itemPedidoController);
        // Instancie os submenus de pedidos
        app.ui.menu.PedidoMenuBuscas pedidoMenuBuscas = new app.ui.menu.PedidoMenuBuscas(pedidoController);
        app.ui.menu.PedidoMenuManip pedidoMenuManip = new app.ui.menu.PedidoMenuManip(pedidoController);

        // Passe os submenus para o PedidoMenu
        app.ui.menu.PedidoMenu pedidoMenu = new app.ui.menu.PedidoMenu(pedidoController, pedidoMenuBuscas , pedidoMenuManip);

        // Initialize the CLI
        InputReader inputReader = new InputReader();
        CLI cli = new CLI(inputReader, pratoMenu, itemPedidoMenu, pedidoMenu);
        cli.start();

    }
}

