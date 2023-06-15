/**
 * @desc OverlayController is the fundament for every single type of overlay. With the right
 * configuration, it can be used to build (modal) dialogs, tooltips, dropdowns, popovers,
 * bottom/top/left/right sheets etc.
 *
 * ### About contentNode, contentWrapperNode and renderTarget.
 *
 * #### contentNode
 * Node containing actual overlay contents.
 * It will not be touched by the OverlayController, it will only set attributes needed
 * for accessibility.
 *
 * #### contentWrapperNode
 * The 'positioning' element.
 * For local overlays, this node will be provided to Popper and all
 * inline positioning styles will be added here. It will also act as the container of an arrow
 * element (the arrow needs to be a sibling of contentNode for Popper to work correctly).
 * When projecting a contentNode from a shadowRoot, it is essential to have the wrapper in
 * shadow dom, so that contentNode can be styled via `::slotted` from the shadow root.
 * The Popper arrow can then be styled from that same shadow root as well.
 * For global overlays, the contentWrapperNode will be appended to the globalRootNode structure.
 *
 * #### renderTarget
 * Usually the parent node of contentWrapperNode that either exists locally or globally.
 * When a responsive scenario is created (in which we switch from global to local or vice versa)
 * we need to know where we should reappend contentWrapperNode (or contentNode in case it's projected)
 *
 * So a regular flow can be summarized as follows:
 * 1. Application Developer spawns an OverlayController with a contentNode reference
 * 2. OverlayController will create a contentWrapperNode around contentNode (or consumes when provided)
 * 3. contentWrapperNode will be appended to the right renderTarget
 *
 * There are subtle differences depending on the following factors:
 * - whether in global/local placement mode
 * - whether contentNode projected
 * - whether an arrow is provided
 *
 * This leads to the following possible combinations:
 * - [l1]. local + no content projection + no arrow
 * - [l2]. local +    content projection + no arrow
 * - [l3]. local + no content projection +    arrow
 * - [l4]. local +    content projection +    arrow
 * - [g1]. global
 *
 * #### html structure for a content projected node
 * <div id="contentWrapperNode">
 *  <slot name="contentNode"></slot>
 *  <div x-arrow></div>
 * </div>
 *
 * Structure above depicts [l4]
 * So in case of [l1] and [l3], the <slot> element would be a regular element
 * In case of [l1] and [l2], there would be no arrow.
 * Note that a contentWrapperNode should be provided for [l2], [l3] and [l4]
 * In case of a global overlay ([g1]), it's enough to provide just the contentNode.
 * In case of a local overlay or a responsive overlay switching from placementMode, one should
 * always configure as if it were a local overlay.
 */
export class OverlayController extends EventTargetShim {
    /**
     * @constructor
     * @param {OverlayConfig} config initial config. Will be remembered as shared config
     * when `.updateConfig()` is called.
     */
    constructor(config?: OverlayConfig, manager?: any);
    manager: any;
    /** @private */
    private __sharedConfig;
    /**
     * @type {OverlayConfig}
     * @protected
     */
    protected _defaultConfig: OverlayConfig;
    /** @protected */
    protected _contentId: string;
    /** @private */
    private __originalAttrs;
    __isContentNodeProjected: boolean;
    /** @private */
    private __hasActiveTrapsKeyboardFocus;
    /** @private */
    private __hasActiveBackdrop;
    /**
     * @type {HTMLElement | undefined}
     * @private
     */
    private __backdropNodeToBeTornDown;
    /** @private */
    private __escKeyHandler;
    /**
     * The invokerNode
     * @type {HTMLElement | undefined}
     */
    get invoker(): HTMLElement | undefined;
    /**
     * The contentWrapperNode
     * @type {HTMLElement}
     */
    get content(): HTMLElement;
    /**
     * Determines the connection point in DOM (body vs next to invoker).
     * @type {'global' | 'local' | undefined}
     */
    get placementMode(): "global" | "local" | undefined;
    /**
     * The interactive element (usually a button) invoking the dialog or tooltip
     * @type {HTMLElement | undefined}
     */
    get invokerNode(): HTMLElement | undefined;
    /**
     * The element that is used to position the overlay content relative to. Usually,
     * this is the same element as invokerNode. Should only be provided when invokerNode should not
     * be positioned against.
     * @type {HTMLElement}
     */
    get referenceNode(): HTMLElement;
    /**
     * The most important element: the overlay itself
     * @type {HTMLElement}
     */
    get contentNode(): HTMLElement;
    /**
     * The wrapper element of contentNode, used to supply inline positioning styles. When a Popper
     * arrow is needed, it acts as parent of the arrow node. Will be automatically created for global
     * and non projected contentNodes. Required when used in shadow dom mode or when Popper arrow is
     * supplied. Essential for allowing webcomponents to style their projected contentNodes
     * @type {HTMLElement}
     */
    get contentWrapperNode(): HTMLElement;
    /**
     * The element that is placed behind the contentNode. When not provided and `hasBackdrop` is true,
     * a backdropNode will be automatically created
     * @type {HTMLElement}
     */
    get backdropNode(): HTMLElement;
    /**
     * The element that should be called `.focus()` on after dialog closes
     * @type {HTMLElement}
     */
    get elementToFocusAfterHide(): HTMLElement;
    /**
     * Whether it should have a backdrop (currently exclusive to globalOverlayController)
     * @type {boolean}
     */
    get hasBackdrop(): boolean;
    /**
     * Hides other overlays when mutiple are opened (currently exclusive to globalOverlayController)
     * @type {boolean}
     */
    get isBlocking(): boolean;
    /**
     * Hides other overlays when mutiple are opened (currently exclusive to globalOverlayController)
     * @type {boolean}
     */
    get preventsScroll(): boolean;
    /**
     * Rotates tab, implicitly set when 'isModal'
     * @type {boolean}
     */
    get trapsKeyboardFocus(): boolean;
    /**
     * Hides the overlay when pressing [ esc ]
     * @type {boolean}
     */
    get hidesOnEsc(): boolean;
    /**
     * Hides the overlay when clicking next to it, exluding invoker
     * @type {boolean}
     */
    get hidesOnOutsideClick(): boolean;
    /**
     * Hides the overlay when pressing esc, even when contentNode has no focus
     * @type {boolean}
     */
    get hidesOnOutsideEsc(): boolean;
    /**
     * Will align contentNode with referenceNode (invokerNode by default) for local overlays.
     * Usually needed for dropdowns. 'max' will prevent contentNode from exceeding width of
     * referenceNode, 'min' guarantees that contentNode will be at least as wide as referenceNode.
     * 'full' will make sure that the invoker width always is the same.
     * @type {'max' | 'full' | 'min' | 'none' | undefined }
     */
    get inheritsReferenceWidth(): "none" | "min" | "max" | "full" | undefined;
    /**
     * For non `isTooltip`:
     *  - sets aria-expanded="true/false" and aria-haspopup="true" on invokerNode
     *  - sets aria-controls on invokerNode
     *  - returns focus to invokerNode on hide
     *  - sets focus to overlay content(?)
     *
     * For `isTooltip`:
     *  - sets role="tooltip" and aria-labelledby/aria-describedby on the content
     *
     * @type {boolean}
     */
    get handlesAccessibility(): boolean;
    /**
     * Has a totally different interaction- and accessibility pattern from all other overlays.
     * Will behave as role="tooltip" element instead of a role="dialog" element
     * @type {boolean}
     */
    get isTooltip(): boolean;
    /**
     * By default, the tooltip content is a 'description' for the invoker (uses aria-describedby).
     * Setting this property to 'label' makes the content function as a label (via aria-labelledby)
     * @type {'label' | 'description'| undefined}
     */
    get invokerRelation(): "label" | "description" | undefined;
    /**
     * Popper configuration. Will be used when placementMode is 'local'
     * @type {PopperOptions}
     */
    get popperConfig(): import("@popperjs/core/lib/types").Options;
    /**
     * Viewport configuration. Will be used when placementMode is 'global'
     * @type {ViewportConfig}
     */
    get viewportConfig(): import("../types/OverlayConfig.js").ViewportConfig;
    /**
     * Usually the parent node of contentWrapperNode that either exists locally or globally.
     * When a responsive scenario is created (in which we switch from global to local or vice versa)
     * we need to know where we should reappend contentWrapperNode (or contentNode in case it's
     * projected).
     * @type {HTMLElement}
     * @protected
     */
    protected get _renderTarget(): HTMLElement;
    /**
     * @desc The element our local overlay will be positioned relative to.
     * @type {HTMLElement | undefined}
     * @protected
     */
    protected get _referenceNode(): HTMLElement | undefined;
    /**
     * @param {string} value
     */
    set elevation(arg: number);
    /**
     * @type {number}
     */
    get elevation(): number;
    /**
     * Allows to dynamically change the overlay configuration. Needed in case the
     * presentation of the overlay changes depending on screen size.
     * Note that this method is the only allowed way to update a configuration of an
     * OverlayController instance.
     * @param { OverlayConfig } cfgToAdd
     */
    updateConfig(cfgToAdd: OverlayConfig): void;
    /**
     * @type {OverlayConfig}
     * @private
     */
    private __prevConfig;
    /** @type {OverlayConfig} */
    config: import("../types/OverlayConfig.js").OverlayConfig | undefined;
    /** @private */
    private __elementToFocusAfterHide;
    /**
     * @param {OverlayConfig} newConfig
     * @private
     */
    private __validateConfiguration;
    /**
     * @param {{ cfgToAdd: OverlayConfig }} options
     * @protected
     */
    protected _init({ cfgToAdd }: {
        cfgToAdd: OverlayConfig;
    }): void;
    /** @private */
    private __initConnectionTarget;
    /**
     * Cleanup ._contentWrapperNode. We do this, because creating a fresh wrapper
     * can lead to problems with event listeners...
     * @param {{ cfgToAdd: OverlayConfig }} options
     * @private
     */
    private __initContentWrapperNode;
    /** config [l2],[l3],[l4] */
    __contentWrapperNode: HTMLElement | undefined;
    /** config [l2], [l4] */
    __originalContentParent: HTMLElement | undefined;
    /**
     * Display local overlays on top of elements with no z-index that appear later in the DOM
     * @param {{ phase: OverlayPhase }} config
     * @protected
     */
    protected _handleZIndex({ phase }: {
        phase: OverlayPhase;
    }): void;
    /**
     * @param {{ phase: OverlayPhase }} config
     * @private
     */
    private __setupTeardownAccessibility;
    /**
     * @param {HTMLElement} node
     * @param {string[]} attrs
     * @private
     */
    private __storeOriginalAttrs;
    /** @private */
    private __restoreOriginalAttrs;
    get isShown(): boolean;
    /**
     * @event before-show right before the overlay shows. Used for animations and switching overlays
     * @event show right after the overlay is shown
     * @param {HTMLElement} elementToFocusAfterHide
     */
    show(elementToFocusAfterHide?: HTMLElement): Promise<void>;
    _showComplete: Promise<any> | undefined;
    _showResolve: ((value?: any) => void) | undefined;
    /**
     * @param {{ phase: OverlayPhase }} config
     * @protected
     */
    protected _handlePosition({ phase }: {
        phase: OverlayPhase;
    }): Promise<void>;
    /**
     * @param {{ phase: OverlayPhase }} config
     * @protected
     */
    protected _keepBodySize({ phase }: {
        phase: OverlayPhase;
    }): void;
    __bodyClientWidth: number | undefined;
    __bodyClientHeight: number | undefined;
    __bodyMarginRightInline: string | undefined;
    __bodyMarginBottomInline: string | undefined;
    __bodyMarginRight: number | undefined;
    __bodyMarginBottom: number | undefined;
    /**
     * @event before-hide right before the overlay hides. Used for animations and switching overlays
     * @event hide right after the overlay is hidden
     */
    hide(): Promise<void>;
    _hideComplete: Promise<any> | undefined;
    _hideResolve: ((value?: any) => void) | undefined;
    /**
     * Method to be overriden by subclassers
     *
     * @param {{backdropNode:HTMLElement, contentNode:HTMLElement}} hideConfig
     */
    transitionHide(hideConfig: {
        backdropNode: HTMLElement;
        contentNode: HTMLElement;
    }): Promise<void>;
    /**
     * @param {{backdropNode:HTMLElement, contentNode:HTMLElement}} hideConfig
     * @protected
     */
    protected _transitionHide(hideConfig: {
        backdropNode: HTMLElement;
        contentNode: HTMLElement;
    }): Promise<void>;
    __backdropAnimation: Promise<any> | undefined;
    /**
     * To be overridden by subclassers
     *
     * @param {{backdropNode:HTMLElement, contentNode:HTMLElement}} showConfig
     */
    transitionShow(showConfig: {
        backdropNode: HTMLElement;
        contentNode: HTMLElement;
    }): Promise<void>;
    /**
     * @param {{backdropNode:HTMLElement, contentNode:HTMLElement}} showConfig
     */
    _transitionShow(showConfig: {
        backdropNode: HTMLElement;
        contentNode: HTMLElement;
    }): Promise<void>;
    /** @protected */
    protected _restoreFocus(): void;
    toggle(): Promise<void>;
    /**
     * All features are handled here.
     * @param {{ phase: OverlayPhase }} config
     * @protected
     */
    protected _handleFeatures({ phase }: {
        phase: OverlayPhase;
    }): void;
    /**
     * @param {{ phase: OverlayPhase }} config
     * @protected
     */
    protected _handlePreventsScroll({ phase }: {
        phase: OverlayPhase;
    }): void;
    /**
     * @param {{ phase: OverlayPhase }} config
     * @protected
     */
    protected _handleBlocking({ phase }: {
        phase: OverlayPhase;
    }): void;
    get hasActiveBackdrop(): boolean;
    /**
     * Sets up backdrop on the given overlay. If there was a backdrop on another element
     * it is removed. Otherwise this is the first time displaying a backdrop, so a animation-in
     * animation is played.
     * @param {{ animation?: boolean, phase: OverlayPhase }} config
     * @protected
     */
    protected _handleBackdrop({ phase }: {
        animation?: boolean | undefined;
        phase: OverlayPhase;
    }): void;
    __backdropNode: HTMLDivElement | undefined;
    get hasActiveTrapsKeyboardFocus(): boolean;
    /**
     * @param {{ phase: OverlayPhase }} config
     * @protected
     */
    protected _handleTrapsKeyboardFocus({ phase }: {
        phase: OverlayPhase;
    }): void;
    enableTrapsKeyboardFocus(): void;
    _containFocusHandler: {
        disconnect: () => void;
    } | undefined;
    disableTrapsKeyboardFocus({ findNewTrap }?: {
        findNewTrap?: boolean | undefined;
    }): void;
    /**
     * @param {{ phase: OverlayPhase }} config
     * @protected
     */
    protected _handleHidesOnEsc({ phase }: {
        phase: OverlayPhase;
    }): void;
    /**
     * @param {{ phase: OverlayPhase }} config
     * @protected
     */
    protected _handleHidesOnOutsideEsc({ phase }: {
        phase: OverlayPhase;
    }): void;
    /** @protected */
    protected _handleInheritsReferenceWidth(): void;
    /**
     * @param {{ phase: OverlayPhase }} config
     * @protected
     */
    protected _handleHidesOnOutsideClick({ phase }: {
        phase: OverlayPhase;
    }): void;
    /** @type {EventListenerOrEventListenerObject} */
    __onInsideMouseDown: EventListener | EventListenerObject | undefined;
    __onInsideMouseUp: (() => void) | undefined;
    /** @type {EventListenerOrEventListenerObject} */
    __onDocumentMouseUp: EventListener | EventListenerObject | undefined;
    /**
     * @param {{ phase: OverlayPhase }} config
     * @protected
     */
    protected _handleAccessibility({ phase }: {
        phase: OverlayPhase;
    }): void;
    teardown(): void;
    /** @protected */
    protected _teardownContentWrapperNode(): void;
    /** @private */
    private __createPopperInstance;
    _popper: any;
}
export namespace OverlayController {
    export const popperModule: Promise<PopperModule> | undefined;
}
export type OverlayConfig = import("../types/OverlayConfig.js").OverlayConfig;
export type ViewportConfig = import("../types/OverlayConfig.js").ViewportConfig;
export type Popper = any;
export type PopperOptions = {
    placement: import("@popperjs/core/lib/enums").Placement;
    modifiers: Partial<import("@popperjs/core/lib/types").Modifier<any, any>>[];
    strategy: import("@popperjs/core/lib/types").PositioningStrategy;
    onFirstUpdate?: ((arg0: Partial<import("@popperjs/core/lib/types").State>) => void) | undefined;
};
export type Placement = "left" | "right" | "top" | "auto" | "auto-start" | "auto-end" | "bottom" | "top-start" | "top-end" | "bottom-start" | "bottom-end" | "right-start" | "right-end" | "left-start" | "left-end";
export type PopperModule = {
    createPopper: any;
};
export type OverlayPhase = "setup" | "teardown" | "remove" | "init" | "before-show" | "show" | "hide" | "add";
import { EventTargetShim } from "@lion/core";
