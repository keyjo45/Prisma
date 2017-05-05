package com.gruponutresa.prisma.business;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.gruponutresa.prisma.dao.ResponsableDao;
import com.gruponutresa.prisma.exception.TechnicalException;
import com.gruponutresa.prisma.models.Responsable;

@RunWith(MockitoJUnitRunner.class)
public class ResponsableBusinessTest {

	@InjectMocks
	private ResponsableBusiness responsableBusiness;

	@Mock
	private ResponsableDao responsableDao;

	@Mock
	private List<Responsable> listaResponsable;

	@Test
	public void debeObtenerREsponsables() {

		Mockito.when(responsableDao.consultarResponsable()).thenReturn(listaResponsable);
		List<Responsable> listaResponbleObtenida = responsableDao.consultarResponsable();

		assertEquals(listaResponsable, listaResponbleObtenida);

		Mockito.verify(responsableDao).consultarResponsable();
	}

	@Test(expected = TechnicalException.class)
	public void debeGenerarExcepcionesConsultandoResponsables() throws TechnicalException {

		List<Responsable> listaResponsableNula=null;

		Mockito.when(responsableDao.consultarResponsable()).thenReturn(listaResponsableNula);
		responsableBusiness.obtenerResponsable();
	}

}
