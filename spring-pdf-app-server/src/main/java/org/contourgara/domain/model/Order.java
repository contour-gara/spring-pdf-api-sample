package org.contourgara.domain.model;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.*;

public record Order(String orderId, Integer amount, String recipientName, String remarks) {
    public Order {
        if (isNull(orderId)) throw new IllegalArgumentException("受注 ID が null であってはならない。");
        if (!orderId.matches("[a-zA-Z0-9]+")) throw new IllegalArgumentException("受注 ID がアルファベットと数字以外であってはならない。[orderId = %s]".formatted(orderId));
        if (isNull(amount)) throw new IllegalArgumentException("金額が null であってはならない。");
        if (amount < 0) throw new IllegalArgumentException("金額が負の値であってはならない。[amount = %s]".formatted(amount));
        if (isNull(recipientName)) throw new IllegalArgumentException("宛名が null であってはならない。");
        if (isBlank(recipientName) && isNotEmpty(recipientName)) throw new IllegalArgumentException("宛名がスペースであってはならない。");
        if (!recipientName.matches("[a-zA-Z0-9 ]*")) throw new IllegalArgumentException("宛名がアルファベットと半角スペース以外であってはならない。[recipientName = %s]".formatted(recipientName));
        if (isNull(remarks)) throw new IllegalArgumentException("但書が null であってはならない。");
        if (isBlank(remarks) && isNotEmpty(remarks)) throw new IllegalArgumentException("但書がスペースであってはならない。");
        if (!remarks.matches("\\p{ASCII}*")) throw new IllegalArgumentException("但書が半角文字以外であってはならない。[remarks = %s]".formatted(remarks));
    }
}
