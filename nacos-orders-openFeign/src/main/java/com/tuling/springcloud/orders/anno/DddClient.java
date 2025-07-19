package com.tuling.springcloud.orders.anno;

import org.springframework.context.annotation.Import;

@Import(DdSelector.class)
public @interface DddClient {
}
