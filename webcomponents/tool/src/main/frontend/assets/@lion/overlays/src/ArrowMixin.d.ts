/**
 * @typedef {import('../types/OverlayConfig').OverlayConfig} OverlayConfig
 * @typedef {import('../types/ArrowMixinTypes').ArrowMixin} ArrowMixin
 * @typedef {import('@popperjs/core/lib/popper').Options} PopperOptions
 * @typedef {import('@popperjs/core/lib/enums').Placement} Placement
 * @typedef {import('@lion/core').CSSResultArray} CSSResultArray
 */
/**
 * @type {ArrowMixin}
 * @param {import('@open-wc/dedupe-mixin').Constructor<import('@lion/core').LitElement>} superclass
 */
export const ArrowMixinImplementation: ArrowMixin;
export type ArrowMixin = typeof import("../types/ArrowMixinTypes.js").ArrowImplementation;
export const ArrowMixin: typeof import("../types/ArrowMixinTypes.js").ArrowImplementation;
export type OverlayConfig = import("../types/OverlayConfig.js").OverlayConfig;
export type PopperOptions = {
    placement: import("@popperjs/core/lib/enums").Placement;
    modifiers: Partial<import("@popperjs/core/lib/types").Modifier<any, any>>[];
    strategy: import("@popperjs/core/lib/types").PositioningStrategy;
    onFirstUpdate?: ((arg0: Partial<import("@popperjs/core/lib/types").State>) => void) | undefined;
};
export type Placement = "left" | "right" | "top" | "auto" | "auto-start" | "auto-end" | "bottom" | "top-start" | "top-end" | "bottom-start" | "bottom-end" | "right-start" | "right-end" | "left-start" | "left-end";
export type CSSResultArray = import("lit-element").CSSResultArray;
