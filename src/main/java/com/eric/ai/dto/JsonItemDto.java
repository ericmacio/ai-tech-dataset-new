package com.eric.ai.dto;

public record JsonItemDto(
        String description,
        String provider,
        String product
) {
    public String getData(String prefix, String separator) {
        return prefix + separator + this.description + separator + this.provider + separator + this.product;
    }
}
