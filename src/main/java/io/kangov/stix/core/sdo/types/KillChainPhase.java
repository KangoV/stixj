package io.kangov.stix.core.sdo.types;


/**
 * kill-chain-phase
 * <p>
 * The kill-chain-phase represents a phase in a kill chain.
 */
public record KillChainPhase(

    String killChainName,
    String phaseName

) {
}
