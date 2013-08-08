package org.cisco.catalog.dao;

import org.cisco.catalog.domain.BlobData;

public interface BlobDataDao {

	void delete(BlobData blobData);

	BlobData findOne(Integer id);

	BlobData save(BlobData blobData);

	BlobData update(BlobData blobData);

}
