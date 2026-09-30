package com.leitzke.apialunos.exception;

import java.time.Instant;

public record ErroResponse(
        int status,
        String mensagem,
        Instant timestamp
) {}
