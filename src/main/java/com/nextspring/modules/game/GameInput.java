package com.nextspring.modules.game;

import java.math.BigDecimal;
import java.util.UUID;

record GameInput(String name, UUID categoryId, BigDecimal price) {
}
