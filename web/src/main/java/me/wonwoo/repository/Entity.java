package me.wonwoo.repository;

import java.util.UUID;

public interface Entity<T extends UUID> {

  T getId();

  void setId(T id);
}
