/**
 * @typedef {import('../../types/OverlayConfig').OverlayConfig} OverlayConfig
 */
/**
 * Compares two OverlayConfigs to equivalence. Intended to prevent unnecessary resets.
 * Note that it doesn't cover as many use cases as common implementations, such as Lodash isEqual.
 *
 * @param {Partial<OverlayConfig>} a
 * @param {Partial<OverlayConfig>} b
 * @returns {boolean} Whether the configs are equivalent
 */
export function isEqualConfig(a: Partial<OverlayConfig>, b: Partial<OverlayConfig>): boolean;
export type OverlayConfig = import("../../types/OverlayConfig").OverlayConfig;
