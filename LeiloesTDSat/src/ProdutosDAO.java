import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        conn = new conectaDAO().connectDB();
         String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
     
         try {
             prep = conn.prepareStatement(sql);
             prep.setString(1, produto.getNome());
             prep.setInt(2, produto.getValor());
             prep.setString(3, produto.getStatus());
             prep.executeUpdate();
             JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
         } catch (SQLException erro) {
             JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + erro.getMessage());
         } finally {
         try {
            prep.close();
            conn.close();
         } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
         }
    }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        conn = new conectaDAO().connectDB();
       String sql = "SELECT * FROM produtos";
     
       try {
         prep = conn.prepareStatement(sql);
         resultset = prep.executeQuery();
         
       while (resultset.next()) {
             ProdutosDTO produto = new ProdutosDTO();
             produto.setId(resultset.getInt("id"));
             produto.setNome(resultset.getString("nome"));
             produto.setValor(resultset.getInt("valor"));
             produto.setStatus(resultset.getString("status"));
             listagem.add(produto);
         }
     } catch (SQLException erro) {
         JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + erro.getMessage());
     } finally {
         try {
             resultset.close();
             prep.close();
             conn.close();
         } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
         }
     }
        
        return listagem;
    }      
}

