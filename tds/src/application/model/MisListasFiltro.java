package application.model;

import java.util.List;

public class MisListasFiltro implements Filtro {

	@Override
	public boolean filtrarVideo(Usuario usuario, Video video) {
		List<ListaVideos> listasVideos = usuario.getListas();
		for (ListaVideos lv : listasVideos) {
			if (lv.containsVideo(video))
				return false;
		}
		return true;		
	}

}