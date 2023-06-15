/**
 * `OverlaysManager` which manages overlays which are rendered into the body
 */
export class OverlaysManager {
    static __createGlobalRootNode(): HTMLDivElement;
    static __createGlobalStyleNode(): HTMLStyleElement;
    /**
     * no setter as .list is intended to be read-only
     * You can use .add or .remove to modify it
     */
    get globalRootNode(): HTMLElement;
    /**
     * no setter as .list is intended to be read-only
     * You can use .add or .remove to modify it
     */
    get list(): import("./OverlayController.js").OverlayController[];
    /**
     * no setter as .shownList is intended to be read-only
     * You can use .show or .hide on individual controllers to modify
     */
    get shownList(): import("./OverlayController.js").OverlayController[];
    /**
     * @type {OverlayController[]}
     * @private
     */
    private __list;
    /**
     * @type {OverlayController[]}
     * @private
     */
    private __shownList;
    /** @private */
    private __siblingsInert;
    /**
     * @type {WeakMap<OverlayController, OverlayController[]>}
     * @private
     */
    private __blockingMap;
    /**
     * Registers an overlay controller.
     * @param {OverlayController} ctrlToAdd controller of the newly added overlay
     * @returns {OverlayController} same controller after adding to the manager
     */
    add(ctrlToAdd: OverlayController): OverlayController;
    /**
     * @param {OverlayController} ctrlToRemove
     */
    remove(ctrlToRemove: OverlayController): void;
    /**
     * @param {OverlayController} ctrlToShow
     */
    show(ctrlToShow: OverlayController): void;
    /**
     * @param {any} ctrlToHide
     */
    hide(ctrlToHide: any): void;
    teardown(): void;
    /** Features right now only for Global Overlay Manager */
    get siblingsInert(): boolean;
    disableTrapsKeyboardFocusForAll(): void;
    /**
     * @param {'local' | 'global' | undefined} placementMode
     */
    informTrapsKeyboardFocusGotEnabled(placementMode: 'local' | 'global' | undefined): void;
    /**
     * @param {{ disabledCtrl?:OverlayController, findNewTrap?:boolean }} [options]
     */
    informTrapsKeyboardFocusGotDisabled({ disabledCtrl, findNewTrap }?: {
        disabledCtrl?: import("./OverlayController.js").OverlayController | undefined;
        findNewTrap?: boolean | undefined;
    } | undefined): void;
    /** PreventsScroll */
    requestToPreventScroll(): void;
    requestToEnableScroll(): void;
    /**
     * Blocking
     * @param {OverlayController} blockingCtrl
     */
    requestToShowOnly(blockingCtrl: OverlayController): void;
    /**
     * @param {OverlayController} blockingCtrl
     */
    retractRequestToShowOnly(blockingCtrl: OverlayController): void;
}
export namespace OverlaysManager {
    export const __globalRootNode: HTMLElement | undefined;
    export const __globalStyleNode: HTMLStyleElement | undefined;
}
export type OverlayController = import("./OverlayController.js").OverlayController;
