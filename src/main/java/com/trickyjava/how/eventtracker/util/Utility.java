package com.trickyjava.how.eventtracker.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public interface Utility {
    static String getClientIPAddress() {
        return Optional.ofNullable(getCurrentHttpRequest())
                .map(request -> {
                    String remoteAddr = "";
                    remoteAddr = request.getHeader("X-FORWARDED-FOR");
                    if (remoteAddr == null || "".equals(remoteAddr)) {
                        remoteAddr = request.getRemoteAddr();
                    }
                    return remoteAddr;
                }).orElse(null);
    }

    static String getUserAgent() {
        return Optional.ofNullable(getCurrentHttpRequest())
                .map(request -> request.getHeader("User-Agent"))
                .orElse(null);
    }

    static HttpServletRequest getCurrentHttpRequest() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(requestAttributes -> requestAttributes instanceof ServletRequestAttributes)
                .map(requestAttributes -> ((ServletRequestAttributes) requestAttributes).getRequest())
                .orElse(null);
    }

    static <R> R evalNoNPE(Supplier<R> supplier) {
        try {
            return supplier.get();
        } catch (Exception npe) {
            return null;
        }
    }
}
