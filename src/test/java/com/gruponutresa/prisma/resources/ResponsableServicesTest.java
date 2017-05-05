package com.gruponutresa.prisma.resources;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import com.gruponutresa.prisma.business.ResponsableBusiness;
import com.gruponutresa.prisma.dao.ResponsableDao;
import com.gruponutresa.prisma.exception.TechnicalException;
import com.gruponutresa.prisma.models.Responsable;


@RunWith(MockitoJUnitRunner.class)
public class ResponsableServicesTest {

	@InjectMocks
	private ResponsableService responsableService;

	@Mock
	private ResponsableBusiness business;
	
	@Mock
	private ResponsableDao dao;

	@Mock
	private List<Responsable> listaResponsable;
	
	private List<Responsable> lista;

	@Test
	public void debeRetornarListaDeResponsables() throws TechnicalException {
		
		Mockito.when(business.obtenerResponsable()).thenReturn(listaResponsable);
		lista = responsableService.consultarResponsable();
		assertNotNull(lista);

	}

}
