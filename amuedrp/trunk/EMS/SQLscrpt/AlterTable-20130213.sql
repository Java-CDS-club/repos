use emsiitk;

CREATE TABLE preferencial_voting (voter_ballot_id VARCHAR(20), position_id VARCHAR(20) NOT NULL, candidate_id VARCHAR(20) NOT NULL, election_id VARCHAR(20) NOT NULL, institute_id VARCHAR(20), voter_id VARCHAR(255) NOT NULL, status VARCHAR(20), preference VARCHAR(20), PRIMARY KEY (candidate_id, election_id, position_id, voter_id));

