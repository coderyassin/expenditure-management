package org.yascode.shared.model;

import lombok.Builder;

@Builder
public record CheckParams(Object object, String message) {
}
