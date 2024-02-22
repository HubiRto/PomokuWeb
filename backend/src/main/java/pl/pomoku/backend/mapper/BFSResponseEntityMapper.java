package pl.pomoku.backend.mapper;

import pl.pomoku.backend.dto.response.BFSResponse;
import pl.pomoku.backend.entity.BFSEntity;

public class BFSResponseEntityMapper implements AbstractMapper<BFSResponse, BFSEntity> {
    @Override
    public BFSEntity toEntity(BFSResponse dto) {
        return null;
    }

    @Override
    public BFSResponse toDto(BFSEntity entity) {
        return new BFSResponse(
                entity.getTraverse(),
                entity.getTreeHeight(),
                entity.getMaxQueueSize(),
                entity.getInternalCount(),
                entity.getExternalCount(),
                entity.getInOperationsCount(),
                entity.getOutOperationsCount()
        );
    }
}
