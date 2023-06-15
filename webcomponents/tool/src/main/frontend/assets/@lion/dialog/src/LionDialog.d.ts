declare const LionDialog_base: typeof LitElement & import("@open-wc/dedupe-mixin").Constructor<import("@lion/overlays/types/OverlayMixinTypes").OverlayHost> & Pick<typeof import("@lion/overlays/types/OverlayMixinTypes").OverlayHost, "prototype"> & Pick<typeof LitElement, "prototype" | "render" | "styles" | "shadowRootOptions" | "getStyles" | "properties" | "observedAttributes" | "createProperty">;
export class LionDialog extends LionDialog_base {
    /** @private */
    private __toggle;
}
import { LitElement } from "@lion/core";
export {};
