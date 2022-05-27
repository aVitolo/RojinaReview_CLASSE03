package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdottoDAO {

    public Prodotto doRetriveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM prodotto WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Prodotto p = new Prodotto();
            if (rs.next()) {
                p.setId(rs.getInt(1));
                p.setNome(rs.getString(2));
                p.setDescrizione(rs.getString(3));
                p.setPrezzo(rs.getFloat(4));
                p.setDisponibilità(rs.getInt(6));

                p.setSconto(null);
                if(rs.getInt(6)==1){
                    ps = con.prepareStatement("SELECT nome, percentuale FROM sconto WHERE prodotto=?");
                    ps.setInt(1, id);
                    rs = ps.executeQuery();
                    p.getSconto().setNome(rs.getString(1));
                    p.getSconto().setPercetuale(rs.getFloat(2));
                }

                ps = con.prepareStatement("SELECT categoria FROM prodotto_categoria WHERE prodotto=?");
                rs = ps.executeQuery();
                while (rs.next())
                    p.getCategorie().add(rs.getString(1));

                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
