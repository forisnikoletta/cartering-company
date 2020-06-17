package edu.ubb.cartering.api.assembler;

import edu.ubb.cartering.api.dto.AbstractDTO;
import edu.ubb.cartering.api.exceptions.ApiException;

import javax.ejb.Local;


@Local
public interface Assembler<M, D extends AbstractDTO> {
	
	D createDto ();
    
    M createModel ();
    
    D modelToDto (M model) throws ApiException;
    
    M dtoToModel (D dto) throws ApiException;
}
