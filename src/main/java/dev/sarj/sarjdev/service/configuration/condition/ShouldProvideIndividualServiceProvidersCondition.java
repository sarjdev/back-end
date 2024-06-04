package dev.sarj.sarjdev.service.configuration.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;


/**
 * {@code ShouldProvideIndividualServiceProvidersCondition} is a condition that always returns false.
 * This condition can be used with Spring's {@link org.springframework.context.annotation.Conditional @Conditional}
 * annotation to conditionally exclude a bean definition based on this criteria.
 *
 * <p>
 * Individual providers referred to in this context include companies such as ZES, AKSA, ASTOR, ESARJ, etc.
 * This condition can be used in scenarios where beans related to these individual service providers are to be
 * conditionally excluded.
 * </p>
 *
 * <pre class="code">
 * &#064;Component
 * &#064;Conditional(ShouldProvideIndividualServiceProvidersCondition.class)
 * public class IndividualServiceProvider {
 *     // Service provider related implementation
 * }
 * </pre>
 *
 * <p>Example usage:</p>
 * <pre class="code">
 * &#064;Configuration
 * public class AppConfig {
 *
 *     &#064;Bean
 *     &#064;Conditional(ShouldProvideIndividualServiceProvidersCondition.class)
 *     public IndividualServiceProvider individualServiceProvider() {
 *         return new IndividualServiceProvider();
 *     }
 * }
 * </pre>
 *
 * @see org.springframework.context.annotation.Conditional
 */
public class ShouldProvideIndividualServiceProvidersCondition implements Condition {

    /**
     * Determines if the condition matches.
     *
     * @param context  the condition context
     * @param metadata the metadata of the {@code @Configuration} class or {@code @Bean} method being checked
     * @return always {@code false}, indicating that the condition is never met
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return false;
    }
}
