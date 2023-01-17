package rojinaReview.model.dao;

import rojinaReview.model.beans.Pagamento;
import rojinaReview.utilities.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PagamentoDAO {
    private final Connection con;

    public PagamentoDAO() throws SQLException {
        con = ConPool.getConnection();
    }

    public PagamentoDAO(Connection con) {
        this.con = con;
    }

    public ArrayList<Pagamento> doRetrieveByUser(int user) throws SQLException {
        PreparedStatement ps =
                con.prepareStatement("SELECT nome, cognome, numeroCarta, dataScadenza FROM pagamento WHERE id_videogiocatore=?");
        ps.setInt(1, user);
        ResultSet rs = ps.executeQuery();
        ArrayList<Pagamento> pagamenti = new ArrayList<>();
        while (rs.next()) {
            Pagamento p = new Pagamento();
            p.setNome(rs.getString(1));
            p.setCognome(rs.getString(2));
            p.setNumeroCarta(rs.getString(3));
            p.setDataScadenza(rs.getDate(4));
            pagamenti.add(p);
        }

        return pagamenti;
    }

    public void doSave(Pagamento p, int user) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO pagamento VALUES (?, ?, ?, ?, ?)");
        ps.setString(1, p.getNome());
        ps.setString(2, p.getCognome());
        ps.setString(3, p.getNumeroCarta());
        ps.setDate(4, p.getDataScadenza());
        ps.setInt(5, user);

        if(ps.executeUpdate() != 1)
            throw new RuntimeException("Insert error");

    }

}
