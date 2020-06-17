package edu.ubb.cartering.api.assembler;

import edu.ubb.cartering.api.dto.AbstractDTO;
import edu.ubb.cartering.backend.model.BaseEntity;

public abstract class EntityAssembler<M extends BaseEntity, D extends AbstractDTO> extends AbstractAssembler<M, D> {
    
}