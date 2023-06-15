/**
 * Whether first element contains the second element, also goes through shadow roots
 * @param {HTMLElement|ShadowRoot} el
 * @param {HTMLElement|ShadowRoot} targetEl
 * @returns {boolean}
 */
export function deepContains(el: HTMLElement | ShadowRoot, targetEl: HTMLElement | ShadowRoot): boolean;
