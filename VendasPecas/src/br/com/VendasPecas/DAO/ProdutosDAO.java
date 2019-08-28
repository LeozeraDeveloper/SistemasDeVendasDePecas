package br.com.VendasPecas.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.VendasPecas.domain.Produto;
import br.com.VendasPecas.util.HibernateUtil;

public class ProdutosDAO {

	Session sessao;
	
	public ProdutosDAO()
	{
		this.sessao = HibernateUtil.getSessionFactory().openSession();
	}
	
	public void salvar(Produto produto) {
		//Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;
		
		

		try {
			transacao = sessao.beginTransaction(); // abrindo a transa��o
			sessao.save(produto);
			transacao.commit(); // confirmando a transa��o

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback(); // desfaz a transa��o
			}

		}

		finally {
			//sessao.close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Produto> listar() {
		//Session sessao = HibernateUtil.getSessionFactory().openSession();

		List<Produto> produto = null;

		try {
			
			Query consulta = sessao.getNamedQuery("Produto.listar");
			produto = consulta.list();
			

		} catch (RuntimeException ex) {
			throw ex;
		}

		finally {
			//sessao.close();
		}
		
		return produto;
	}
	
	public Produto buscarPorCodigo(Long codigo) {
		//Session sessao = HibernateUtil.getSessionFactory().openSession();

		Produto produto = null;

		try {
			
			Query consulta = sessao.getNamedQuery("Produto.buscarPorCodigo");
			consulta.setLong("codigo", codigo);
			produto = (Produto) consulta.uniqueResult();
			

		} catch (RuntimeException ex) {
			System.out.println("Vai dar erro " + ex.getMessage());
			throw ex;
		}

		finally {
			//sessao.close();
		}
		
		return produto;
	}
	
	public void excluir(Produto produto) {
		//Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction(); // abrindo a transa��o
			sessao.delete(produto);
			transacao.commit(); // confirmando a transa��o

		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback(); // desfaz a transa��o
			}

		}

		finally {
			//sessao.close();
		}

	}
	
	public void editar(Produto produto) {
		//Session sessao = HibernateUtil.getSessionFactory().openSession();

		Transaction transacao = null;

		try {

			System.out.println("Atualizando produto " + produto.getCodigo().toString());
			transacao = sessao.beginTransaction(); // abrindo a transa��o
			
			sessao.update(produto);
			transacao.commit(); // confirmando a transa��o

		} catch (RuntimeException ex) {
			System.out.println("Erro atualizando produto " + produto.getCodigo().toString());
			if (transacao != null) {
				transacao.rollback(); // desfaz a transa��o
			}
		}

		finally {
			//sessao.close();
		}
	}

	public void Dispose()
	{
		System.out.println("Vai fechar sessao");
		sessao.close();
		System.out.println("Abriu sessao");
	}
}