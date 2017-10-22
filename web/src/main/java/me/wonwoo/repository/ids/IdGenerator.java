package me.wonwoo.repository.ids;

import java.util.UUID;

public interface IdGenerator {

  <T extends UUID> T getId();
}
