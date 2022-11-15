package bean;

import static util.MessageUtil.addErrorMessage;
import static util.MessageUtil.addInfoMessage;

import java.util.List;

import javax.faces.bean.ManagedBean;

import dao.JogoDao;
import entidade.Jogo;

@ManagedBean
public class JogoBean {

	private Jogo jogo = new Jogo();
	private List<Jogo> listaJogo;
	private int maiorValor;
	
	public String salvar() 
	{
		JogoDao.salvar(getJogo());
		jogo = new Jogo();
		addInfoMessage("Sucesso", "Adicionado com sucesso.");
		return null;
	}
	
	public String excluir() 
	{
		try {
			JogoDao.excluir(getJogo());
			addInfoMessage("Sucesso", "Item excluído com sucesso.");
			setListaJogo(JogoDao.listar());
		} catch (Exception e) {
			System.out.println(e);
			addErrorMessage("Erro", "Erro ao excluir Jogo.");
		}
		return null;
	}
	
	public String editarJogo() 
	{
		System.out.println("testeedit");
		try {
			System.out.println(jogo.getId());
			jogo.setV1(jogo.getV1());
			jogo.setV2(jogo.getV2());
			jogo.setV3(jogo.getV3());
			jogo.setV4(jogo.getV4());
			jogo.setV5(jogo.getV5());
			JogoDao.editar(jogo);
			addInfoMessage("Sucesso", "Jogo editado com sucesso.");
		} catch (Exception e) {
			System.out.println(e);
			addErrorMessage("Erro", "Erro ao editar Jogo.");
		}
		
		return "listagem.xhtml";
	}
	
	public String redirectEdit()
	{
		return "editarJogo.xhtml";
	}

	public Jogo getJogo() 
	{
		return jogo;
	}

	public void setJogo(Jogo jogo) 
	{
		this.jogo = jogo;
	}

	public List<Jogo> getListaJogo() 
	{
		if(listaJogo == null)
			listaJogo = JogoDao.listar();
		return listaJogo;
	}

	public void setListaJogo(List<Jogo> listaJogo) 
	{
		this.listaJogo = listaJogo;
	}

	public int getMaiorValor() 
	{
		if(this.jogo.getV1() == null)
			return this.maiorValor;
		int valores[] = new int[5];
		int maior = 0;
		valores[0]=this.jogo.getV1();
		valores[1]=this.jogo.getV2();
		valores[2]=this.jogo.getV3();
		valores[3]=this.jogo.getV4();
		valores[4]=this.jogo.getV5();		
		
		for (int i : valores) 
			if(i>maior)
				maior = i;
		
		return maior;
	}

	public void setMaiorValor(int maiorValor) 
	{
		this.maiorValor = maiorValor;
	}
}
