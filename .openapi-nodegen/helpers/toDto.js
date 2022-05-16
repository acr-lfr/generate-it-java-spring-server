"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.toDto = void 0;
const toDto = (name) => (!name || name.includes('.') ? name : `com.acrontum.template.dtos.${name}Dto`);
exports.toDto = toDto;
exports.default = exports.toDto;
