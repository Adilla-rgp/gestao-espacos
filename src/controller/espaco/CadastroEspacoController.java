package controller.espaco;

import view.CadastroEspacoView;
import controller.app.AplicacaoController;
import model.exceptions.LocaisException;
import model.exceptions.UsuarioException;
import javax.swing.JOptionPane;

public class CadastroEspacoController {
    private CadastroEspacoView cadastroEspacoView;
    private AplicacaoController controller;

    public CadastroEspacoController(CadastroEspacoView cadastroEspacoView, AplicacaoController controller){
        this.cadastroEspacoView = cadastroEspacoView;
        this.controller = controller;

        cadastroEspacoView.adicionarCancelarButtonListener(e -> controller.mostrarTelaDashboard());
        cadastroEspacoView.adicionarSalvarButtonListener(e -> salvarEspaco());
    }
    
    private void salvarEspaco() {
        try {
            // Verificar se usuário é administrador
            if (!controller.getUsuario().getStatusAdm()) {
                throw new UsuarioException.UsuarioNaoPermitidoException(controller.getUsuario().getNome());
            }
            
            // Obter dados básicos da tela
            String nome = cadastroEspacoView.getNome();
            String tipo = cadastroEspacoView.getTipo();
            String capacidad = cadastroEspacoView.getCapacidade();
            int capacidade = Integer.parseInt(capacidad);
            
            // Validações essenciais
            if (nome == null || nome.trim().isEmpty()) {throw new LocaisException("Nome do espaço é obrigatório"); }
            
            if (tipo == null || tipo.trim().isEmpty()) { throw new LocaisException("Tipo do espaço é obrigatório");}
            
            if (capacidade <= 0) {throw new LocaisException("Capacidade deve ser maior que zero"); }
            
            // Validar tipo de espaço
            if (!tipoEspacoValido(tipo)) { throw new LocaisException.TipoEspacoInvalidoException(tipo); }
            
            // Simular salvamento bem-sucedido
            JOptionPane.showMessageDialog(cadastroEspacoView, "Espaço '" + nome + "' cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            
        } catch (LocaisException.EspacoJaCriadoException e) {
            JOptionPane.showMessageDialog(cadastroEspacoView, "Já existe um espaço com este nome", "Erro", JOptionPane.ERROR_MESSAGE);
            
        } catch (LocaisException.TipoEspacoInvalidoException e) {
            JOptionPane.showMessageDialog(cadastroEspacoView, "Tipo de espaço inválido. Use: Sala, Laboratório, Auditório, Quadra",  "Erro", JOptionPane.ERROR_MESSAGE);
            
        } catch (UsuarioException.UsuarioNaoPermitidoException e) {
            JOptionPane.showMessageDialog(cadastroEspacoView, "Apenas administradores podem cadastrar espaços", "Acesso Negado", JOptionPane.ERROR_MESSAGE);
            
        } catch (LocaisException e) {
            JOptionPane.showMessageDialog(cadastroEspacoView, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(cadastroEspacoView, "Erro interno do sistema", "Erro",  JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean tipoEspacoValido(String tipo) {
        String[] tiposValidos = {"sala", "laboratório", "laboratorio", "auditório", "auditorio", "quadra", "campo"};
        
        for (String tipoValido : tiposValidos) {
            if (tipo.toLowerCase().contains(tipoValido)) {
                return true;
            }
        }
        return false;
    }
    
}