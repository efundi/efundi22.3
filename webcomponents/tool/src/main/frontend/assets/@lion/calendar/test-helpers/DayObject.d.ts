/**
 * Abstraction around calendar day DOM structure,
 * allows for writing readable, 'DOM structure agnostic' tests
 */
export class DayObject {
    /**
     * @param {HTMLElement} dayEl
     */
    constructor(dayEl: HTMLElement);
    el: HTMLElement;
    /**
     * Node references
     */
    get calendarShadowRoot(): (Node & ParentNode) | null | undefined;
    get cellEl(): HTMLElement;
    get buttonEl(): HTMLElement;
    /**
     * States
     */
    get isDisabled(): boolean;
    get isSelected(): boolean;
    get isToday(): boolean;
    get isCentral(): boolean;
    get isFocused(): boolean;
    get monthday(): number;
    /**
     * Text
     */
    get weekdayNameShort(): string;
    get weekdayNameLong(): string;
    /**
     * Other
     */
    get cellIndex(): number;
}
