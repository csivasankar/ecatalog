package org.cisco.catalog.service;

import org.cisco.catalog.domain.BlobData;

public interface BlobDataService {

	public abstract void deleteBlobData(BlobData blobData);

	public abstract BlobData findBlobData(Integer id);

	public abstract BlobData saveBlobData(BlobData blobData);

	public abstract BlobData updateBlobData(BlobData blobData);
}
