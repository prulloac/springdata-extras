package com.prulloac.springdataextras.restquery.nodes.comparison;

import com.prulloac.springdataextras.restquery.BaseRestQueryTest;
import com.prulloac.springdataextras.restquery.specification.RestQuery;
import com.prulloac.springdataextras.schema.DummyEntity;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;

class DistinctNodeTest extends BaseRestQueryTest {

  @Test
  public void getPredicate() {
    String query = "field!=test";
    String query2 = "field!=test,testB";
    RestQuery<DummyEntity> restQuery = RestQuery.buildQuery(DummyEntity.class, query);
    RestQuery<DummyEntity> restQuery2 = RestQuery.buildQuery(DummyEntity.class, query2);
    List<DummyEntity> results = dummyEntityRepository.findAll(restQuery);
    List<DummyEntity> results2 = dummyEntityRepository.findAll(restQuery2);
    assertThat(
        results.stream().map(DummyEntity::getField).collect(Collectors.toList()), hasItem("testB"));
    assertThat(results2, empty());
  }
}
