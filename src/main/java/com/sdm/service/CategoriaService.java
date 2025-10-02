package com.sdm.service;

import com.sdm.dao.CategoriaDAO;
import com.sdm.model.Categoria;
import java.util.List;

public class CategoriaService {
    private CategoriaDAO dao = new CategoriaDAO();

    public void salvar(Categoria categoria) {
        dao.salvar(categoria);
    }

    public void atualizar(Categoria categoria) {
        dao.atualizar(categoria);
    }

    public void deletar(int id) {
        dao.deletar(id);
    }

    public List<Categoria> listar() {
        return dao.listar();
    }

    public Categoria buscarPorId(int id) {
        return dao.buscarPorId(id);
    }
}
